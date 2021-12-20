<?php
include 'conexion.php';

$correoUsuario=$_POST['correoUsuario'];
$contrasenaUsuario=$_POST['contrasenaUsuario'];

$sentencia=$conexion->prepare("SELECT * FROM usuario WHERE correoUsuario=? AND contrasenaUsuario=?");
$sentencia->bind_param('ss',$correoUsuario,$contrasenaUsuario);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>