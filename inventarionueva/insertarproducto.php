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

$consulta="insert into producto values('".$codigoProducto."','".$imagenProducto."','".$piezasProducto."','".$precioProducto."','".$idTipoProducto."','".$idCategoriaProducto."','".$idMarcaProducto."','".$idCantidadProducto."','".$idUnidadProducto."','".$codigoInterno."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>