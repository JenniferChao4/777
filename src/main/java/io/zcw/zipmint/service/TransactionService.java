package io.zcw.zipmint.service;

import io.github.jhipster.web.util.ResponseUtil;
import io.zcw.zipmint.domain.Transaction;
import io.zcw.zipmint.domain.enumeration.TransactionType;
import io.zcw.zipmint.repository.TransactionRepository;
import io.zcw.zipmint.web.rest.errors.BadRequestAlertException;
import io.zcw.zipmint.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private static final String ENTITY_NAME = "transaction";

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        if (transaction.getId() != null) {
            throw new BadRequestAlertException("A new transaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transaction result = transactionRepository.save(transaction);
        return ResponseEntity.created(new URI("/api/transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        if (transaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transaction result = transactionRepository.save(transaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transaction.getId().toString()))
            .body(result);
    }

    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transaction);
    }

    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public ResponseEntity<Iterable<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(sortByDate(transactionRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Transaction>> getDebitTransactions(){
        Iterable<Transaction> transactions = transactionRepository.findAll()
            .stream()
            .filter(this::isDebit)
            .collect(Collectors.toList());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Transaction>> getCreditTransactions(){
        Iterable<Transaction> transactions = transactionRepository.findAll()
            .stream()
            .filter(this::isCredit)
            .collect(Collectors.toList());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Transaction>> getSortedByCategory(){
        return new ResponseEntity<>(sortByCategory(transactionRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Transaction>> getSortedByDescription(){
        return new ResponseEntity<>(sortByDescription(transactionRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Transaction>> getSortedByAmount(){
        return new ResponseEntity<>(sortByAmount(transactionRepository.findAll()), HttpStatus.OK);
    }

    private List<Transaction> sortByDate(List<Transaction> transactionList) {
        transactionList.sort(Comparator.comparing(o -> o.getDateTime()));
        return transactionList;
    }

    private List<Transaction> sortByCategory(List<Transaction> transactionList) {
        transactionList.sort(Comparator.comparing(o -> o.getCategory()));
        return transactionList;
    }

    private List<Transaction> sortByDescription(List<Transaction> transactionList) {
        transactionList.sort(Comparator.comparing(o -> o.getDescription()));
        return transactionList;
    }

    private List<Transaction> sortByAmount(List<Transaction> transactionList) {
        transactionList.sort(Comparator.comparing(o -> o.getAmount()));
        return transactionList;
    }

    private boolean isDebit(Transaction transaction){
        return transaction.getTransactionType().equals(TransactionType.DEBIT);
    }

    private boolean isCredit(Transaction transaction){
        return !isDebit(transaction);
    }

}