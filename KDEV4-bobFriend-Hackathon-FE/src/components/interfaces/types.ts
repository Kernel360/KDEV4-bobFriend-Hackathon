// types.ts 파일로 분리 가능

export interface ApiResponse {
  body: any
  pagination: Pagination
  resultCode?: string | null
  resultMessage?: string | null
  error?: ErrorResponse | null
}

export interface Place {
  id: number
  name: string
  content: string
  address: string
  category: string
}

export interface User {
  userId: number
  name: string
  email: string
  isActive?: boolean
  createdAt?: string
  updatedAt?: string
}

export interface Gathering {
  id: number
  title: string
  content: string
  gathering_at: string
  closing_at: string
  created_at: string
  max_participant: number
  state: string
  talk_thema: string
  talk_flag: string
  user_name: number
  current_participant: number
  participant_list: User[]
  place: Place
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
