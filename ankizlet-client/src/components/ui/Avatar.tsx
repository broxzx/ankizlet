interface AvatarProps {
    src: string;
    alt?: string;
}

export function Avatar({ src, alt }: AvatarProps) {
    return (
        <img
            src={src}
            alt={alt}
            className="w-10 h-10 rounded-full border border-gray-300 object-cover"
        />
    );
}
