<?php
    include 'Conexion.clase.php';
    class CategoriaProducto extends Conexion{
        public function listar(){
            $sql = "SELECT categoriaproducto.idCategoriaProducto, categoriaproducto.categoria FROM categoriaproducto";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
        }