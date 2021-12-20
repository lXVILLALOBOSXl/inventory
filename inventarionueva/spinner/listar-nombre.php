<?php
    include 'NombreProducto.clase.php';
    $objNombre = new NombreProducto();
    $resultado = $objNombre->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);   
?>