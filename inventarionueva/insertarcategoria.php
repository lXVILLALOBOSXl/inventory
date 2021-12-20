<?php

include 'conexion.php';
$idTipoCategoriaProducto=$_POST['idTipoCategoriaProducto'];
$categoria=$_POST['categoria'];

$consulta="insert into categoriaproducto values('".$idTipoCategoriaProducto."','".$categoria."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>