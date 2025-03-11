// types.ts 파일로 분리 가능

export interface ApiResponse {
  body: any
  pagination: Pagination
  resultCode?: string | null
  resultMessage?: string | null
  error?: ErrorResponse | null
}

export interface Post {
  id: number
  board_id: number
  user_id: number
  user_name: string
  title: string
  artist: string
  content: string
  link: string
  status: string
  created_at: number[] // [년, 월, 일, 시, 분, 초] 형태의 배열
  reply_list: Reply[] | null
}

export interface Pagination {
  page: number
  size: number
  current_element: number
  total_page: number
  total_element: number
}

export interface ErrorResponse {
  message: string
  code?: string
}

export interface Reply {
  // 댓글 관련 데이터 (현재 null이므로 구조가 확실하지 않음)
}

export interface UserResponse {
  name: string
}
