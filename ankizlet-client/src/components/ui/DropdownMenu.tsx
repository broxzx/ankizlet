import { useState, ReactNode } from "react";

interface DropdownMenuProps {
    trigger: ReactNode;
    children: ReactNode;
}

export function DropdownMenu({ trigger, children }: DropdownMenuProps) {
    const [open, setOpen] = useState(false);

    return (
        <div className="relative">
            <div onClick={() => setOpen(!open)} className="cursor-pointer">
                {trigger}
            </div>
            {open && (
                <div className="absolute right-0 mt-2 w-48 bg-white shadow-lg rounded-lg border border-gray-200">
                    {children}
                </div>
            )}
        </div>
    );
}

export function DropdownMenuItem({ children }: { children: ReactNode }) {
    return (
        <div className="px-4 py-2 hover:bg-gray-100 cursor-pointer">{children}</div>
    );
}
