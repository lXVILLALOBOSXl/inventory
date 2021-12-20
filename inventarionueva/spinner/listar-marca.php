<?php
    include 'MarcaProducto.clase.php';
    $objMarca = new MarcaProducto();
    $resultado = $objMarca->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);   
?>