import { useState } from "react";
import { motion } from "framer-motion";
import { Search, PlusCircle } from "lucide-react";

export default function FlashcardApp() {
  const [search, setSearch] = useState<string>("");
  
  return (
    <div className="min-h-screen bg-gray-50 flex flex-col items-center p-6">
      <motion.h1
        className="text-5xl font-bold text-blue-600 mb-6"
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}>
        Learn Smarter
      </motion.h1>
      
      <div className="flex items-center space-x-2 w-full max-w-lg mb-6">
        <input
          type="text"
          placeholder="Search flashcards..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="flex-grow border border-gray-300 rounded-lg px-3 py-2"
        />
        <button className="border border-gray-300 rounded-lg p-2">
          <Search className="w-5 h-5 text-gray-600" />
        </button>
      </div>
      
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 w-full max-w-5xl">
        {/* Flashcard items will be added here */}
      </div>
      
      <button className="mt-8 flex items-center space-x-2 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition">
        <PlusCircle className="w-5 h-5" />
        <span>Add Flashcard</span>
      </button>
    </div>
  );
}