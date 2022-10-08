package ru.tagallteam.hackstarter.application.transfer.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.transfer.mapper.TransferMapper;
import ru.tagallteam.hackstarter.application.transfer.model.TransferCreateModel;
import ru.tagallteam.hackstarter.application.transfer.model.TransferDto;
import ru.tagallteam.hackstarter.application.transfer.service.TransferService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.Status;

@Api(tags = "Управление переводами")
@RestController
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;


    @PostMapping(Endpoints.TransferService.TRANSFER)
    public void transfer(@PathVariable Long userId, @RequestBody TransferCreateModel transfer){
        transferService.transfer(userId, transfer);
    }

    @GetMapping(Endpoints.TransferService.TRANSFER_STATUS)
    public Status transferStatus(@PathVariable String txHash){
        return transferService.transferStatus(txHash);
    }

    @GetMapping(Endpoints.TransferService.TRANSFER_INFO)
    public TransferDto transferModel(@PathVariable String txHash){
        return transferService.transferModel(txHash);
    }
}
