<?php

include 'conexion.php';
$idTipoProducto=$_POST['idTipoProducto'];
$nombreProducto=$_POST['nombreProducto'];
$descripcionProducto=$_POST['descripcionProducto'];

$consulta="insert into tipoproducto values('".$idTipoProducto."','".$nombreProducto."','".$descripcionProducto."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>