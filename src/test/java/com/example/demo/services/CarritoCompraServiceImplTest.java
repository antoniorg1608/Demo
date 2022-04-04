package com.example.demo.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {
	
	
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosI baseDatos;
	

	

	@Test
	public void testLimpiarCesta() {  //Limpiar Cesta
		carritoService.addArticulo(new Articulo("Camiseta", 20.00));
		assertFalse(carritoService.getArticulos().isEmpty());  // comprobamos que no esta vacia
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());  //comprobamos que esta vacia y es verdadero
		
	}

	@Test
	public void testAddArticulo() {  //Añadir articulo
		carritoService.addArticulo(new Articulo("Camiseta", 20.00));	
		carritoService.addArticulo(new Articulo("Pantalon", 25.00));
		assertFalse(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testGetNumArticulos() { //Devuelve el número de artículos de la cesta
		
		carritoService.addArticulo(new Articulo("Camiseta", 20.00));	
		carritoService.addArticulo(new Articulo("Pantalon", 25.00));
		Integer res =carritoService.getNumArticulos();
		assertEquals(Integer.valueOf(2), res);
	}

	@Test
	public void testGetArticulos() {   //Devuelve la lista de todos los artículos añadidos​
		
		carritoService.addArticulo(new Articulo("Camiseta", 20.00));	
		carritoService.addArticulo(new Articulo("Pantalon", 25.00));
		List<Articulo>res =carritoService.getArticulos();
		assertEquals(2, res.size());
	}

	@Test
	public void testTotalPrice() { //Devuelve el precio total de la compra​
		carritoService.addArticulo(new Articulo("Camiseta", 20.00));	
		carritoService.addArticulo(new Articulo("Pantalon", 25.00));
		Double res =carritoService.totalPrice();
		assertEquals(Double.valueOf(45.00D),res);
		
		
	}

	@Test
	public void testCalculadorDescuento() { // 
		assertEquals(Double.valueOf(90D), carritoService.calculadorDescuento(100D, 10D));
	} 
	
	@Test
	public void testaplicarDescuento() {  
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.buscarArticulo(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1,10D);
		assertEquals(Double.valueOf(18D), res);
		
		
	}

}
