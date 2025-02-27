import { useState } from "react";
import { motion } from "framer-motion";
import { Home, Folder, Bell, User } from "lucide-react";
import { Card } from "/ui/Card";
import { Input } from "/ui/Input";
import { Button } from "/ui/Button";
import { Avatar } from "/ui/Avatar";
import { DropdownMenu, DropdownMenuItem } from "/ui/DropdownMenu.tsx";

const Sidebar = ({ isOpen }: { isOpen: boolean }) => {
    return (
        <motion.div
            initial={{ width: 60 }}
            animate={{ width: isOpen ? 200 : 60 }}
            className="h-screen bg-gray-100 p-4 flex flex-col items-start border-r shadow-lg"
        >
            <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: isOpen ? 1 : 0 }}
                className="flex flex-col gap-6 w-full mt-10"
            >
                {[{ icon: <Home />, text: "Home" }, { icon: <Folder />, text: "My Sets" }, { icon: <Bell />, text: "Notifications" }].map((item, index) => (
                    <motion.div key={index} className="flex items-center gap-4 p-2 cursor-pointer hover:bg-gray-200 rounded-lg">
                        {item.icon}
                        {isOpen && <span>{item.text}</span>}
                    </motion.div>
                ))}
            </motion.div>
        </motion.div>
    );
};

export default function HomePage() {
    const [sidebarOpen, setSidebarOpen] = useState(false);

    return (
        <div className="flex h-screen">
            <Sidebar isOpen={sidebarOpen} />
            <div className="flex-1 p-6">
                <div className="flex justify-between items-center mb-6">
                    <Input placeholder="Search sets..." className="w-1/2" />
                    <DropdownMenu>
                        <DropdownMenuTrigger>
                            <Avatar className="cursor-pointer">
                                <AvatarImage src="https://via.placeholder.com/40" alt="User Avatar" />
                            </Avatar>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent>
                            <DropdownMenuItem>Profile</DropdownMenuItem>
                            <DropdownMenuItem>Settings</DropdownMenuItem>
                            <DropdownMenuItem>Achievements</DropdownMenuItem>
                            <DropdownMenuItem>Logout</DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>
                </div>
                <div className="grid grid-cols-3 gap-4">
                    {[1, 2, 3].map((id) => (
                        <Card key={id} className="p-4 hover:shadow-lg transition-shadow">
                            <CardContent>
                                <h2 className="text-lg font-semibold">Set {id}</h2>
                                <p className="text-gray-500">Some description...</p>
                                <Button className="mt-2">Open</Button>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            </div>
        </div>
    );
}
