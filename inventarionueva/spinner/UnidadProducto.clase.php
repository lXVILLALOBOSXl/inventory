<?php
    include 'Conexion.clase.php';
    class UnidadProducto extends Conexion{
        public function listar(){
            $sql = "SELECT unidadproducto.idUnidadProducto, unidadproducto.unidad FROM unidadproducto";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
        }
?>