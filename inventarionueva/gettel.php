<?php

include 'conexion.php';

$codigoProducto=$_GET['codigoProducto'];

$consultaTel = "SELECT marcaproducto.telefonoMarca from marcaproducto inner join producto on producto.idMarcaProducto = marcaproducto.idMarcaProducto where codigoProducto = '$codigoProducto'";
$resultado =$conexion -> query($consultaTel);

while($fila=$resultado -> fetch_array()){
    $tel[] = array_map('utf8_encode', $fila);
}

echo json_encode($tel);
$resultado -> close();

?>