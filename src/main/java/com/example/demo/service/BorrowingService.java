package com.example.demo.service;

import com.example.demo.model.entity.Borrowing;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.BorrowingRepository;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private UserRepository userRepository;

    // Calculate late fees for overdue borrowings
    public void calculateLateFees() {
        LocalDateTime now = LocalDateTime.now();
        List<Borrowing> borrowings = borrowingRepository.findAll();

        for (Borrowing borrowing : borrowings) {
            if (borrowing.getDueDate().isBefore(now) && borrowing.getLateFee() == 0) {
                long overdueDays = ChronoUnit.DAYS.between(borrowing.getDueDate(), now);
                double fee = overdueDays * 1.0; // $1 per overdue day
                borrowing.setLateFee(fee);

                if (!borrowing.isNotificationSent()) {
                    borrowing.setNotificationSent(true); // Mark notification as sent
                }

                borrowingRepository.save(borrowing); // Save updated borrowing record
            }
        }
    }

    public List<Borrowing> getBorrowingsByUser(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }



    // Return a borrowed book
    public boolean returnBook(Long borrowingId) {
        Optional<Borrowing> borrowingOptional = borrowingRepository.findById(borrowingId);
        if (borrowingOptional.isPresent()) {
            borrowingRepository.delete(borrowingOptional.get());
            return true;
        }
        return false;
    }

    // Save a new borrowing record
    public void saveBorrowing(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }

    // Get a borrowing record by its ID
    public Optional<Borrowing> getBorrowingById(Long borrowingId) {
        return borrowingRepository.findById(borrowingId);
    }
}
