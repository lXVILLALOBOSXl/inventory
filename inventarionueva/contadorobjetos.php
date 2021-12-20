<?php

include 'conexion.php';

$consultaNumero = "select count(*) from producto";
$resultado =$conexion -> query($consultaNumero);

while($fila=$resultado -> fetch_array()){
    $numero[] = array_map('utf8_encode', $fila);
}

echo json_encode($numero);
$resultado -> close();

?>