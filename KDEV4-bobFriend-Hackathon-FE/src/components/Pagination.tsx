interface PaginationProps {
  currentPage: number
  totalPages: number
  onPageChange: (page: number) => void
}

const Pagination: React.FC<PaginationProps> = ({
  currentPage,
  totalPages,
  onPageChange
}) => {
  const goToPage = (page: number) => {
    onPageChange(page) // setCurrentPage
  }
  return (
    <div>
      <button
        onClick={() => goToPage(currentPage - 1)}
        disabled={currentPage === 1}>
        이전
      </button>
      <span>
        {' '}
        {currentPage} / {totalPages}{' '}
      </span>
      <button
        onClick={() => goToPage(currentPage + 1)}
        disabled={currentPage === totalPages}>
        다음
      </button>
    </div>
  )
}
export default Pagination
