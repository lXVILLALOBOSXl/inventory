<?php

include 'conexion.php';

$codigoProducto=$_GET['codigoProducto'];

$consultaLista = "select producto.imagenProducto from producto where codigoProducto = '$codigoProducto'";
$resultado =$conexion -> query($consultaLista);

while($fila=$resultado -> fetch_array()){
    $lista[] = array_map('utf8_encode', $fila);
}

echo json_encode($lista);
$resultado -> close();

?>