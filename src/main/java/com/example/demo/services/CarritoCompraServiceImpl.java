package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Articulo;

public class CarritoCompraServiceImpl implements CarritoCompraServiceI{
	
	@Autowired
	private BaseDatosI baseDatos;
	
	private List<Articulo> cesta= new ArrayList<>();

	@Override
	public void limpiarCesta() {
		cesta.clear();
		
	}

	@Override
	public void addArticulo(Articulo articulo) {
		cesta.add(articulo);
		
	}

	@Override
	public Integer getNumArticulos() {
		// TODO Auto-generated method stub
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		// TODO Auto-generated method stub
		return cesta;
	}

	@Override
	public Double totalPrice() {
		
		Double total= 0D;
		for(Articulo articulo:cesta) {
			total = total +articulo.getPrecio();
			
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentaje) {
		
		
		return precio - precio*porcentaje/100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		
		baseDatos.iniciar();
		
		Articulo articulo = baseDatos.buscarArticulo(idArticulo);
		
		if(Optional.ofNullable(articulo).isPresent()) {
			
			return calculadorDescuento(articulo.getPrecio(), porcentaje);
		}else {
			System.out.println("No se ha encontrado el articulo con ID" + idArticulo);
		}
		
		return 0D;
	}

}
