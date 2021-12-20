<?php

include 'conexion.php';
$codigoProducto=$_POST['codigoProducto'];
$imagenProducto=$_POST['imagenProducto'];
$piezasProducto=$_POST['piezasProducto'];
$precioProducto=$_POST['precioProducto'];
$idTipoProducto=$_POST['idTipoProducto'];
$idCategoriaProducto=$_POST['idCategoriaProducto'];
$idMarcaProducto=$_POST['idMarcaProducto'];
$idCantidadProducto=$_POST['idCantidadProducto'];
$idUnidadProducto=$_POST['idUnidadProducto'];
$codigoInterno=$_POST['codigoInterno'];

$consulta="update producto set codigoInterno = '".$codigoInterno."', imagenProducto = '".$imagenProducto."', piezasProducto = '".$piezasProducto."', precioProducto = '".$precioProducto."', idTipoProducto = '".$idTipoProducto."', idCategoriaProducto = '".$idCategoriaProducto."', idMarcaProducto = '".$idMarcaProducto."', idCantidadProducto = '".$idCantidadProducto."',  idUnidadProducto = '".$idUnidadProducto."' where codigoInterno = '".$codigoProducto."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>