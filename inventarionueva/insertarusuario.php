<?php

include 'conexion.php';
$nombreUsuario=$_POST['nombreUsuario'];
$apellidoPaternoUsuario=$_POST['apellidoPaternoUsuario'];
$apellidoMaternoUsuario=$_POST['apellidoMaternoUsuario'];
$telefonoUsuario=$_POST['telefonoUsuario'];
$correoUsuario=$_POST['correoUsuario'];
$contrasenaUsuario=$_POST['contrasenaUsuario'];

$consulta="insert into usuario values('".$nombreUsuario."','".$apellidoPaternoUsuario."','".$apellidoMaternoUsuario."','".$telefonoUsuario."','".$correoUsuario."','".$contrasenaUsuario."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>