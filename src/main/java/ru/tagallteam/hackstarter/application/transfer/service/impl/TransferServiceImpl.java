package ru.tagallteam.hackstarter.application.transfer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tagallteam.hackstarter.application.transfer.domain.Transfer;
import ru.tagallteam.hackstarter.application.transfer.domain.TransferRepository;
import ru.tagallteam.hackstarter.application.transfer.mapper.TransferMapper;
import ru.tagallteam.hackstarter.application.transfer.model.TransferCreateModel;
import ru.tagallteam.hackstarter.application.transfer.model.TransferDto;
import ru.tagallteam.hackstarter.application.transfer.model.TransferType;
import ru.tagallteam.hackstarter.application.transfer.service.TransferService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.SendCurrency;
import ru.tagallteam.hackstarter.integration.modal.SendNft;
import ru.tagallteam.hackstarter.integration.modal.Status;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final VtbIntegration integration;

    private final UserRepository userRepository;

    private final UserService userService;

    private final TransferMapper transferMapper;

    @Override
    @Transactional
    public void transfer(Long userId, TransferCreateModel transfer) {
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userSend = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        if (transfer.getType().equals(TransferType.DR)) {
            SendCurrency sendCurrency = SendCurrency.builder()
                    .amount(transfer.getAmount())
                    .fromPrivateKey(userSend.getPrivateKey())
                    .toPublicKey(user.getPublicKey()).build();
            Transfer transferEntity = new Transfer();
            transferEntity.setKey(integration.sendRuble(sendCurrency).getTransaction());
            transferEntity.setUserSend(userSend);
            transferEntity.setUserGet(user);
            transferRepository.save(transferEntity);
        } else {
            SendNft sendNft = SendNft.builder()
                    .tokenId(transfer.getNftId())
                    .fromPrivateKey(userSend.getPrivateKey())
                    .toPublicKey(user.getPublicKey()).build();
            Transfer transferEntity = new Transfer();
            transferEntity.setKey(integration.sendNft(sendNft).getTransaction());
            transferEntity.setUserSend(userSend);
            transferEntity.setUserGet(user);
            transferRepository.save(transferEntity);
        }
        userService.addXPtoUser(userId, 20L);
    }

    @Override
    public Status transferStatus(String txHash) {
        ErrorDescriptor.TRANSFER_NOT_FOUND.throwIsFalse(transferRepository.existsByKey(txHash));
        return integration.statusTransaction(txHash);
    }

    @Override
    public TransferDto transferModel(String txHash) {
        Transfer transfer = transferRepository.getByKey(txHash)
                .orElseThrow(ErrorDescriptor.TRANSFER_NOT_FOUND::applicationException);
        return transferMapper.convertToTransferDto(transfer, integration.statusTransaction(txHash));
    }
}
