package org.csu.coderlee.service.custom;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 17:41
 */
public interface IProcessImportService {

    Process process = null;

    void getProcess(Long processId);

    void setProcess(Process process);

    void cacheProcess(Process process);
}
