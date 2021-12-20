<?php

include 'conexion.php';
$idTipoCantidadProducto=$_POST['idTipoCantidadProducto'];
$cantidad=$_POST['cantidad'];

$consulta="insert into cantidadproducto values('".$idTipoCantidadProducto."','".$cantidad."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>