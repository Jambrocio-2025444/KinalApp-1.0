package com.joseAmbrocio.KinalApp.service;

import com.joseAmbrocio.KinalApp.entity.Cliente;
import com.joseAmbrocio.KinalApp.entity.Usuarios;
import com.joseAmbrocio.KinalApp.entity.Ventas;
import com.joseAmbrocio.KinalApp.repository.ClienteRepository;
import com.joseAmbrocio.KinalApp.repository.UsuarioRepository;
import com.joseAmbrocio.KinalApp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentasService{

    private VentasRepository ventasRepository;
    private ClienteRepository clienteRepository;
    private UsuarioRepository usuarioRepository;

    public VentaService(VentasRepository ventasRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.ventasRepository = ventasRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Ventas> listarTodos() {
        return ventasRepository.findAll();
    }

    @Override
    public Optional<Ventas> buscarVenta(int codigoVenta) {
        return ventasRepository.findById((long) codigoVenta);
    }


    @Override
    @Transactional
    public Ventas guardarVenta(Ventas venta) {
        Cliente cliente = clienteRepository.findById(venta.getCliente().getDPICliente()).orElse(null);
        Usuarios usuarios = usuarioRepository.findById(venta.getUsuario().getCodigoUsuario()).orElse(null);

        venta.setCliente(cliente);
        venta.setUsuario(usuarios);

        if (venta.getEstado() == 0) {
            venta.setEstado(1);
        }

        return ventasRepository.save(venta);
    }


    @Override
    public Ventas actualizarVenta(int codigoVenta, Ventas ventas) {
        if (!ventasRepository.existsById((long) codigoVenta)) {
            throw new RuntimeException(" No se encontro la venta" + codigoVenta);
        }
        ventas.setCodigoVenta((long) codigoVenta);
        validarVentas(ventas);

        return ventasRepository.save(ventas);
    }

    @Override
    @Transactional
    public void eliminarVenta(int codigoVenta) {
        if(!ventasRepository.existsById((long) codigoVenta)) {
            throw new RuntimeException(" No se encontro la venta" + codigoVenta);
        }
        ventasRepository.deleteById((long) codigoVenta);
    }

    @Override
    public boolean existeVenta(int codigoVenta) {
        return ventasRepository.existsById((long) codigoVenta);
    }

    @Override
    public List<Ventas> activos() {
        return ventasRepository.findByestado(1);
    }

    private void validarVentas(Ventas venta) {
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria");
        }

        if (venta.getTotal() == null || venta.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El total deber ser un mayor o igual a 0");
        }
    }
}
