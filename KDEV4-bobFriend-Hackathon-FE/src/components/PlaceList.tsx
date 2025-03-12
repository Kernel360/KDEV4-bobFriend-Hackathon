import React, { useEffect, useState } from 'react'
import { ApiResponse, Place, User } from './interfaces/types'
import Api from './Api'
import Pagination from './Pagination'

type PlaceCategory = 'KOR' | 'JPN' | 'CHN' | 'WES' // 예시로 몇 가지 카테고리 지정

export default function PlaceList({ user }: { user: User | null }) {
  const [places, setPlaces] = useState<Place[]>([])
  const [loading, setLoading] = useState<boolean>(true)
  const [currentPage, setCurrentPage] = useState<number>(1)
  const [totalPages, setTotalPages] = useState<number>(1)
  const [isFormOpen, setIsFormOpen] = useState<boolean>(false)

  // 새 장소를 추가하기 위한 상태
  const [newPlace, setNewPlace] = useState({
    name: '',
    content: '',
    address: '',
    category: 'KOR' as PlaceCategory // 기본값은 한식
  })

  const fetchGet = async () => {
    try {
      const response = await Api.get('http://localhost:8080/bobfriend/places', {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      setPlaces(response.data)
      setLoading(false)
    } catch (error) {
      console.error('Failed to fetch places', error)
      setLoading(false)
    }
  }

  const fetchPost = async () => {
    console.log(newPlace)
    try {
      const response = await Api.post(
        'http://localhost:8080/bobfriend/places',
        {
          name: newPlace.name,
          content: newPlace.content || '', // content는 선택 사항
          address: newPlace.address,
          category: newPlace.category
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      console.log('Place added', response.data)
      fetchGet() // 새로 추가된 Place 목록을 다시 불러옴
    } catch (error) {
      console.error('Failed to add place', error)
    }
  }

  useEffect(() => {
    fetchGet()
  }, [currentPage])

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target
    setNewPlace(prev => ({
      ...prev,
      [name]: value
    }))
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    fetchPost()
  }

  if (loading)
    return <p className="text-center text-xl text-gray-600">⏳ 로딩 중...</p>

  return (
    <div className="mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
      <h2 className="mb-4 text-3xl font-bold text-gray-700">📌 게시글 목록</h2>

      {/* 폼 열기/닫기 버튼 */}
      <button
        type="button"
        onClick={() => setIsFormOpen(prev => !prev)}
        className="mb-4 rounded-md bg-black px-4 py-2 text-white">
        {isFormOpen ? '폼 닫기' : '장소 추가하기'}
      </button>

      {/* Place 추가 폼 */}
      {isFormOpen && (
        <form
          onSubmit={handleSubmit}
          className="mb-6">
          <div className="mb-4">
            <label
              htmlFor="name"
              className="block text-gray-700">
              장소 이름
            </label>
            <input
              type="text"
              id="name"
              name="name"
              value={newPlace.name}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label
              htmlFor="content"
              className="block text-gray-700">
              설명 (선택)
            </label>
            <input
              type="text"
              id="content"
              name="content"
              value={newPlace.content}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
            />
          </div>

          <div className="mb-4">
            <label
              htmlFor="address"
              className="block text-gray-700">
              주소
            </label>
            <input
              type="text"
              id="address"
              name="address"
              value={newPlace.address}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label
              htmlFor="category"
              className="block text-gray-700">
              카테고리
            </label>
            <select
              id="category"
              name="category"
              value={newPlace.category}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required>
              <option value="KOR">한식</option>
              <option value="JPN">일식</option>
              <option value="CHN">중식</option>
              <option value="WES">양식</option>
            </select>
          </div>

          <button
            type="submit"
            className="rounded-md bg-black px-4 py-2 text-white">
            추가!
          </button>
        </form>
      )}

      {/* 기존 Place 목록 */}
      <ul className="space-y-4">
        {places.map(place => (
          <li
            key={place.id}
            className="rounded-lg bg-gray-50 p-4 shadow-md transition-all hover:shadow-xl">
            <div className="mb-2 flex items-center justify-between">
              <button>{place.category}</button>
              <strong className="text-xl font-semibold text-gray-900">
                {place.name}
              </strong>
            </div>
            <p className="text-gray-700">{place.content}</p>
            <div className="mt-3">
              {/* <a
                href={place.address}
                target="_blank"
                rel="noopener noreferrer"
                className="text-blue-500 hover:text-blue-700">
                지도에서 보기
              </a> */}
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
