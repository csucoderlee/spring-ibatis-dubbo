package org.csu.coderlee.service.machine.feature;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 23:14
 */
public class NonNumericExtract extends AbstractNonNumericExtract{

    //非数值类特征的特征抽取主要采用变量编码的方法。变量编码方式主要包括LabelEncoder和OneHotEncoder

    String labelEncoder;
    String oneHotEncoder;
    void variableEncoede(String encoder){}
}
