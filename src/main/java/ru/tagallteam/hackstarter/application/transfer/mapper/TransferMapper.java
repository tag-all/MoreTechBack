package ru.tagallteam.hackstarter.application.transfer.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.transfer.domain.Transfer;
import ru.tagallteam.hackstarter.application.transfer.model.TransferDto;
import ru.tagallteam.hackstarter.integration.modal.Status;

@Component
public class TransferMapper {
    public TransferDto convertToTransferDto(Transfer transfer, Status status){
        TransferDto transferDto = new TransferDto();
        transferDto.setUserTo(transfer.getUserSend().getId());
        transferDto.setUserFrom(transfer.getUserGet().getId());
        transferDto.setTxHash(transfer.getKey());
        transferDto.setStatus(status);
        return transferDto;
    }
}
