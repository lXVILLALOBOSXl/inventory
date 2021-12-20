<?php

include 'conexion.php';

$codigoProducto=$_GET['codigoProducto'];

$consultaVer = "select producto.imagenProducto, producto.codigoInterno, tipoProducto.nombreProducto, marcaProducto.nombreMarca, cantidadProducto.cantidad, unidadproducto.unidad, producto.piezasProducto, producto.precioProducto, categoriaProducto.categoria, producto.imagenProducto, tipoproducto.descripcionProducto from producto inner join tipoproducto on tipoproducto.idTipoProducto = producto.idTipoProducto inner join categoriaproducto on categoriaproducto.idCategoriaProducto=producto.idCategoriaProducto inner join unidadproducto on unidadproducto.idUnidadProducto=producto.idUnidadProducto inner join cantidadproducto on cantidadproducto.idCantidadProducto=producto.idCantidadProducto inner join marcaproducto on marcaproducto.idMarcaProducto = producto.idMarcaProducto where codigoProducto = '$codigoProducto'";
$resultado =$conexion -> query($consultaVer);

while($fila=$resultado -> fetch_array()){
    $ver[] = array_map('utf8_encode', $fila);
}

echo json_encode($ver);
$resultado -> close();

?>