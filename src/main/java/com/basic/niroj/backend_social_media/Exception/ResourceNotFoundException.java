package com.basic.niroj.backend_social_media.Exception;

    public class ResourceNotFoundException extends  RuntimeException{


        String resourceName;
        String fieldName;
        Object fieldValue;


        public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {


            super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));

            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;

        }


    }

