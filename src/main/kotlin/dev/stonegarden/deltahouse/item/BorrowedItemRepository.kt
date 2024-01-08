package dev.stonegarden.deltahouse.item

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BorrowedItemRepository : JpaRepository<BorrowedItemDAO, Long>