<?php
    include 'Conexion.clase.php';
    class CantidadProducto extends Conexion{
        public function listar(){
            $sql = "SELECT cantidadproducto.idCantidadProducto, cantidadproducto.cantidad FROM cantidadproducto";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
        }
?>