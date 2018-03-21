package org.csu.coderlee.service.machine;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 22:34
 */
public class MissingValueFillingProcessing extends AbstractMissingValueProcessing {

    private String modeFilling;
    private String randomFilling;
    private String mediumFilling;

    void FillingProcessing(IFillingStrategy fillingStrategy) {}

    @Override
    public void deleteData(IData data) {

    }
}
