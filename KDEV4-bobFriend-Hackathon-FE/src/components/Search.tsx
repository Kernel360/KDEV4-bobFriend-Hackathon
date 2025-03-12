import { useState } from 'react'

export default function Search() {
  const [searchTerm, setSearchTerm] = useState('')
  const [selectedCategory, setSelectedCategory] = useState('')

  const categories = ['음식', '여행', 'IT', '운동']

  const handleCategoryClick = (category: string) => {
    setSelectedCategory(category)
  }

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault()
    alert(`검색어: ${selectedCategory} ${searchTerm}`)
  }

  return (
    <div className="flex items-center justify-center">
      <div className="items-between flex gap-4 rounded-lg bg-white p-4 shadow-lg">
        {/* 카테고리 버튼 */}
        <div className="flex flex-col gap-2">
          {categories.map(category => (
            <button
              key={category}
              className={`rounded-md px-4 py-2 text-white transition-all ${
                selectedCategory === category ? 'bg-blue-600' : 'bg-gray-500'
              }`}
              onClick={() => handleCategoryClick(category)}>
              {category}
            </button>
          ))}
        </div>

        {/* 검색창 */}
        <form
          className="flex h-10 items-center rounded-full border border-gray-300 px-4"
          onSubmit={handleSearch}>
          <input
            type="text"
            className="flex-1 p-2 outline-none"
            placeholder="검색어 입력..."
            value={searchTerm}
            onChange={e => setSearchTerm(e.target.value)}
          />
          <button
            type="submit"
            className="ml-2 rounded-full bg-black px-4 py-2 text-white">
            🔍
          </button>
        </form>
      </div>
    </div>
  )
}
