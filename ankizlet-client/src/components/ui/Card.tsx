import { ReactNode } from "react";

interface CardProps {
    children: ReactNode;
}

export function Card({ children }: CardProps) {
    return (
        <div className="bg-white shadow-md rounded-xl p-4 border border-gray-200">
            {children}
        </div>
    );
}
