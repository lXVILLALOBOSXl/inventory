<?php

include 'conexion.php';
$codigoProducto=$_GET['codigoProducto'];

$consultaUsuario = "select * from producto where codigoInterno = '$codigoProducto'";
$resultado = $conexion -> query($consultaUsuario);

while($fila=$resultado -> fetch_array()){
    $producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>