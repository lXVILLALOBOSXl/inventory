<?php

include 'conexion.php';
$idTipoProducto=$_GET['idTipoProducto'];

$consultaTipo = "select * from tipoproducto where idTipoProducto = '$idTipoProducto'";
$resultado = $conexion -> query($consultaTipo);

while($fila=$resultado -> fetch_array()){
    $tipo[] = array_map('utf8_encode', $fila);
}

echo json_encode($tipo);
$resultado -> close();
?>