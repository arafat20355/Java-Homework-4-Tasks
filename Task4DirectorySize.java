import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Task4DirectorySize {

    private static String formatSize(long bytes) {
        double kb = bytes / 1024.0;
        double mb = bytes / (1024.0 * 1024.0);
        return String.format(Locale.US, "%.2f MB | %.2f KB | %d bytes", mb, kb, bytes);
    }

    // Prints the size of each directory, including all its nested files.
    private static long calculateDirectorySize(Path directory, String indent) {
        long directFilesSize = 0;
        long totalSize = 0;

        try (DirectoryStream<Path> items = Files.newDirectoryStream(directory)) {
            for (Path item : items) {
                // Do not follow symbolic links (prevents cycles and double counting).
                if (Files.isSymbolicLink(item)) {
                    continue;
                }
                if (Files.isDirectory(item)) {
                    totalSize += calculateDirectorySize(item, indent + "    ");
                } else if (Files.isRegularFile(item)) {
                    try {
                        long size = Files.size(item);
                        directFilesSize += size;
                        totalSize += size;
                    } catch (IOException | SecurityException e) {
                        System.out.println(indent + "Cannot read file: " + item);
                    }
                }
            }
        } catch (IOException | SecurityException e) {
            System.out.println(indent + "Cannot read directory: " + directory
                               + " (" + e.getMessage() + ")");
        }

        System.out.println(indent + "Directory: " + directory.toAbsolutePath());
        System.out.println(indent + "  Direct files: " + formatSize(directFilesSize));
        System.out.println(indent + "  Total including subdirectories: " + formatSize(totalSize));
        return totalSize;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter directory path: ");
        String input = scanner.nextLine().trim();

        try {
            Path directory = Paths.get(input);
            if (!Files.exists(directory)) {
                System.out.println("Error: Directory does not exist.");
            } else if (!Files.isDirectory(directory)) {
                System.out.println("Error: The given path is not a directory.");
            } else {
                System.out.println("Directory sizes (subdirectories first):");
                long total = calculateDirectorySize(directory, "");
                System.out.println("\nTOTAL SIZE: " + formatSize(total));
            }
        } catch (InvalidPathException e) {
            System.out.println("Error: Invalid directory path.");
        } finally {
            scanner.close();
        }
    }
}
