package com.example.clientservice.service;

import com.example.clientservice.dto.FacturaDto;
import com.example.clientservice.dto.LineaDto;
import com.example.clientservice.exception.MensajeException;
import com.example.clientservice.model.ClienteModel;
import com.example.clientservice.model.FacturaModel;
import com.example.clientservice.model.LineaModel;
import com.example.clientservice.model.ProductoModel;
import com.example.clientservice.reository.ClienteRepository;
import com.example.clientservice.reository.FacturaRepository;
import com.example.clientservice.reository.LineaRepository;
import com.example.clientservice.reository.ProductoRepository;
import com.example.clientservice.wordclock.WordClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FacturaService {


    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private LineaRepository lineaRepository;
    @Autowired
    private RestTemplate restTemplate;

    public FacturaDto create(FacturaDto facturaDto){

        ClienteModel cliente = clienteRepository.findById(facturaDto.getCliente().getClienteId())
                .orElseThrow(()-> new MensajeException("Cliente no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        int catidadProductos=0;

        Set<LineaDto> lineasDto = facturaDto.getLineas();
        Set<LineaModel> lineasModel = new HashSet<>();

        for (LineaDto lineaDto : lineasDto){
            ProductoModel producto = productoRepository.findById(lineaDto.getProducto().getProductoId())
                    .orElseThrow(()-> new MensajeException("Producto no encontrado"));

            System.out.println();
            String descripcion = (lineaDto.getDescripcion() != null && !lineaDto.getDescripcion().isEmpty())
                ? lineaDto.getDescripcion() : producto.getDescripcion();

            LineaModel lineaModel = new LineaModel();
            lineaModel.setCantidad(lineaDto.getCantidad());
            lineaModel.setDescripcion(descripcion);
            lineaModel.setPrecio(producto.getPrecio());
            lineaModel.setProducto(producto);
            lineaModel.setFactura(null);



            lineasModel.add(lineaModel);

            total =total.add(producto.getPrecio().multiply(BigDecimal.valueOf(lineaDto.getCantidad())));
            catidadProductos +=lineaDto.getCantidad();

            productoRepository.save(producto);
        }

        Date fecha = obtenerFecha();

        FacturaModel facturaModel= new FacturaModel();
        facturaModel.setCantidad(catidadProductos);
        facturaModel.setFecha(fecha);
        facturaModel.setCliente(cliente);
        facturaModel.setTotal(total);



        facturaModel = facturaRepository.save(facturaModel);

        if (!lineasModel.isEmpty()){
            for (LineaModel lineaModel : lineasModel) {
                lineaModel.setFactura(facturaModel);
                facturaModel.addLinea(lineaModel);
            }

            facturaRepository.save(facturaModel);

            actualizarStock(lineasModel);

        } else {
            throw new MensajeException("No hay lineas en la factura");
        }

        return FacturaModelToDto(facturaModel);


    }


    private Date obtenerFecha() {
        WordClock worldClock = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WordClock.class);

        String currentDateTime = worldClock.getCurrentDateTime();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.parse(currentDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private void actualizarStock (Set<LineaModel> lineasModel) {
        for (LineaModel lineaModel : lineasModel){
            ProductoModel producto = lineaModel.getProducto();
            int catidadVenta = lineaModel.getCantidad();

            if(producto.getCantidad() < catidadVenta){
                throw new MensajeException("Stock insuficiente");
            }

            producto.setCantidad(producto.getCantidad() - catidadVenta);
            productoRepository.save(producto);
        }
    }

    private FacturaDto FacturaModelToDto (FacturaModel facturaModel){
        FacturaDto facturaDto =new FacturaDto();
        facturaDto.setFacturaId(facturaModel.getFacturaId());
        facturaDto.setCantidad(facturaModel.getCantidad());
        facturaDto.setFecha(facturaModel.getFecha());
        facturaDto.setTotal(facturaModel.getTotal());
        facturaDto.setCliente(facturaModel.getCliente());
        facturaDto.setLineas(LineaModelToDto(facturaModel.getLineas()));
        return facturaDto;
    }

    private Set<LineaDto> LineaModelToDto(Set<LineaModel> lineasModel){
        Set<LineaDto> lineasDto = new HashSet<>();
        for (LineaModel lineaModel : lineasModel){
            LineaDto lineaDto = new LineaDto();
            lineaDto.setLineaId(lineaModel.getLineaId());
            lineaDto.setCantidad(lineaModel.getCantidad());
            lineaDto.setDescripcion(lineaModel.getDescripcion());
            lineaDto.setPrecio(lineaModel.getPrecio());

            System.out.println();
            lineasDto.add(lineaDto);
        }
        return lineasDto;
    }

    public  FacturaDto getFacturaById (Integer id){
        FacturaModel facturaModel=facturaRepository.findById(id)
                .orElseThrow(()-> new MensajeException("Factura no encontrada con ID: " + id));
        facturaModel.getLineas().size();

        return FacturaModelToDto(facturaModel);
    }


}

