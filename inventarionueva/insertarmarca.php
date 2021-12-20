<?php

include 'conexion.php';
$idMarcaProducto=$_POST['idMarcaProducto'];
$nombreMarca=$_POST['nombreMarca'];
$paisMarca=$_POST['paisMarca'];
$sitioWebMarca=$_POST['sitioWebMarca'];
$telefonoMarca=$_POST['telefonoMarca'];

$consulta="insert into marcaproducto values('".$idMarcaProducto."','".$nombreMarca."','".$paisMarca."','".$sitioWebMarca."','".$telefonoMarca."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>