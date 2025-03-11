import { useState, useEffect } from 'react'
import Api from './Api' // Api 모듈은 적절히 수정되어야 합니다.
import { data } from 'react-router-dom'

export default function GatheringList() {
  interface Gathering {
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
    user_name: string
  }

  const [gatherings, setGatherings] = useState<Gathering[]>([]) // Gathering 타입의 배열
  const [loading, setLoading] = useState<boolean>(true) // 로딩 상태
  const [currentPage, setCurrentPage] = useState<number>(1) // 현재 페이지
  const [totalPages, setTotalPages] = useState<number>(1) // 전체 페이지 수

  const fetchGatherings = async () => {
    try {
      const response = await Api.get(
        'http://localhost:8080/bobfriend/gatherings', // 적절한 API URL로 수정
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      setGatherings(response.data)
      console.log(data)
      // setTotalPages(response.data.pagination.total_page) // 페이지네이션 로직 추가 필요
      setLoading(false)
    } catch (error) {
      console.error('Gathering 데이터 불러오기 실패:', error)
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchGatherings()
  }, [currentPage])

  if (loading) {
    return <p className="text-center text-xl text-gray-600">⏳ 로딩 중...</p>
  }

  return (
    <div className="mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
      <h2 className="mb-4 text-3xl font-bold text-gray-700">📌 모임 목록</h2>
      <ul className="space-y-4">
        {gatherings.map(gathering => (
          <li
            key={gathering.id}
            className="rounded-lg bg-gray-50 p-4 shadow-md transition-all hover:shadow-xl">
            <div className="mb-2 flex items-center justify-between">
              <strong className="text-xl font-semibold text-gray-900">
                {gathering.title}
              </strong>
              <span className="text-sm text-gray-500">
                주최자: {gathering.user_name}
              </span>
            </div>
            <p className="text-gray-700">{gathering.content}</p>
            <div className="mt-3">
              <p className="text-gray-500">
                모임 일시: {gathering.gathering_at}
              </p>
              <p className="text-gray-500">
                모임 종료 일시: {gathering.closing_at}
              </p>
              <p className="text-gray-500">
                참가 최대 인원: {gathering.max_participant}
              </p>
              <p className="text-gray-500">상태: {gathering.state}</p>
            </div>
          </li>
        ))}
      </ul>
      {/* 페이지네이션 기능 추가 필요 */}
      {/* <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={setCurrentPage}
      /> */}
    </div>
  )
}
