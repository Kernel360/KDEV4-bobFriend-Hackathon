import React, { useEffect, useState } from 'react'
import { ApiResponse, Place, UserResponse } from './interfaces/types'
import Api from './Api'
import Pagination from './Pagination'

const PostList: React.FC = () => {
  const [places, setPlaces] = useState<Place[]>([])
  const [loading, setLoading] = useState<boolean>(true)
  const [currentPage, setCurrentPage] = useState<number>(1)
  const [totalPages, setTotalPages] = useState<number>(1)

  const fetchPost = async () => {
    const response = await Api.get(
      'http://localhost:8080/places', // ?page=' + (currentPage - 1)
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    setPlaces(response.data)
    //setTotalPages(response.data.pagination.total_page)
    setLoading(false)
  }

  useEffect(() => {
    fetchPost()
  }, [currentPage])

  if (loading)
    return <p className="text-center text-xl text-gray-600">⏳ 로딩 중...</p>

  return (
    <div className="mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
      <h2 className="mb-4 text-3xl font-bold text-gray-700">📌 게시글 목록</h2>
      <ul className="space-y-4">
        {places.map(place => (
          <li
            key={place.id}
            className="rounded-lg bg-gray-50 p-4 shadow-md transition-all hover:shadow-xl">
            <div className="mb-2 flex items-center justify-between">
              <strong className="text-xl font-semibold text-gray-900">
                {place.name}
              </strong>
              <span className="text-sm text-gray-500">
                작성자: {place.content}
              </span>
            </div>
            <p className="text-gray-700">{place.content}</p>
            <div className="mt-3">
              <a
                href={place.address}
                target="_blank"
                rel="noopener noreferrer"
                className="text-blue-500 hover:text-blue-700"></a>
            </div>
          </li>
        ))}
      </ul>
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={setCurrentPage}
      />
    </div>
  )
}

export default PostList
