package com.trade.util;

import org.springframework.validation.ObjectError;

public class ObjectErrorFactory{

private ObjectErrorFactory(){}

public static ObjectError getObject(String var,String error){
return new ObjectError(var,error);
}
}
