<?php
include 'conexion.php';
$contador=$_POST['contador'];

$consulta="call reiniciar()";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>