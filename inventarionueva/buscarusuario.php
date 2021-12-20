<?php

include 'conexion.php';
$correoUsuario=$_GET['correoUsuario'];

$consultaUsuario = "select * from usuario where correoUsuario = '$correoUsuario'";
$resultado = $conexion -> query($consultaUsuario);

while($fila=$resultado -> fetch_array()){
    $usuario[] = array_map('utf8_encode', $fila);
}

echo json_encode($usuario);
$resultado -> close();

?>