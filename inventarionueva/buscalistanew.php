<?php

include 'conexion.php';

$codigoProducto=$_GET['codigoProducto'];

$consultaLista = "select tipoproducto.nombreProducto, marcaproducto.nombreMarca, cantidadproducto.cantidad, unidadproducto.unidad, producto.codigoProducto from producto inner join tipoproducto on tipoproducto.idTipoProducto = producto.idTipoProducto inner join marcaproducto on marcaproducto.idMarcaProducto = producto.idMarcaProducto inner join cantidadproducto on cantidadproducto.idCantidadProducto = producto.idCantidadProducto inner join unidadproducto on unidadproducto.idUnidadProducto = producto.idUnidadProducto where codigoProducto = '$codigoProducto'";
$resultado =$conexion -> query($consultaLista);

while($fila=$resultado -> fetch_array()){
    $lista[] = array_map('utf8_encode', $fila);
}

echo json_encode($lista);
$resultado -> close();

?>