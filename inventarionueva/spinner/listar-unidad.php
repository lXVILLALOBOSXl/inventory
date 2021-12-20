<?php
    include 'UnidadProducto.clase.php';
    $objCantidad = new UnidadProducto();
    $resultado = $objCantidad->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);   
?>