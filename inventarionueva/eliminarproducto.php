<?php
include 'conexion.php';
$codigoProducto=$_POST['codigoProducto'];

$consulta="delete from producto where codigoProducto = '".$codigoProducto."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>