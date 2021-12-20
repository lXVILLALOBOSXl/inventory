<?php

include 'conexion.php';
$nombreUsuario=$_POST['nombreUsuario'];
$apellidoPaternoUsuario=$_POST['apellidoPaternoUsuario'];
$apellidoMaternoUsuario=$_POST['apellidoMaternoUsuario'];
$telefonoUsuario=$_POST['telefonoUsuario'];
$correoUsuario=$_POST['correoUsuario'];
$contrasenaUsuario=$_POST['contrasenaUsuario'];

$consulta="update usuario set nombreUsuario = '".$nombreUsuario."', apellidoPaternoUsuario = '".$apellidoPaternoUsuario."', apellidoMaternoUsuario = '".$apellidoMaternoUsuario."', telefonoUsuario = '".$telefonoUsuario."', contrasenaUsuario = '".$contrasenaUsuario."' where correoUsuario = '".$correoUsuario."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>