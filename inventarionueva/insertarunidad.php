<?php

include 'conexion.php';
$idTipoUnidadProducto=$_POST['idTipoUnidadProducto'];
$unidad=$_POST['unidad'];

$consulta="insert into unidadproducto values('".$idTipoUnidadProducto."','".$unidad."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>