package dev.luna5ama.glwrapper.base

import java.io.InputStream
import java.io.OutputStream
import java.net.URI
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.FileChannel
import java.nio.channels.SeekableByteChannel
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileAttribute
import java.nio.file.attribute.FileAttributeView
import java.nio.file.attribute.UserPrincipalLookupService
import java.nio.file.spi.FileSystemProvider
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.function.Consumer
import kotlin.io.path.Path
import kotlin.io.path.toPath

abstract class ShaderPathResolver {
    protected abstract fun resolve0(path: String): Path

    fun resolve(path: String): Path {
        return PathImpl(this, resolve0(path))
    }

    private object DelegateFileSystemProvider : FileSystemProvider() {
        override fun getScheme(): String {
            throw UnsupportedOperationException()
        }

        override fun newFileSystem(
            uri: URI,
            env: Map<String?, *>
        ): FileSystem {
            throw UnsupportedOperationException()
        }

        override fun getFileSystem(uri: URI): FileSystem {
            throw UnsupportedOperationException()
        }

        override fun getPath(uri: URI): Path {
            throw UnsupportedOperationException()
        }

        override fun newByteChannel(
            path: Path?,
            options: Set<OpenOption>,
            vararg attrs: FileAttribute<*>
        ): SeekableByteChannel {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().newByteChannel(delegate, options!!, *attrs)
        }

        override fun newDirectoryStream(
            dir: Path,
            filter: DirectoryStream.Filter<in Path>
        ): DirectoryStream<Path> {
            val delegate = (dir as PathImpl).delegate
            return delegate.fileSystem.provider().newDirectoryStream(delegate, filter)
        }

        override fun createDirectory(
            dir: Path,
            vararg attrs: FileAttribute<*>
        ) {
            val delegate = (dir as PathImpl).delegate
            delegate.fileSystem.provider().createDirectory(delegate, *attrs)
        }

        override fun delete(path: Path) {
            val delegate = (path as PathImpl).delegate
            delegate.fileSystem.provider().delete(delegate)
        }

        override fun copy(
            source: Path,
            target: Path,
            vararg options: CopyOption
        ) {
            val delegate1 = (source as PathImpl).delegate
            val delegate2 = (target as PathImpl).delegate
            delegate1.fileSystem.provider().copy(delegate1, delegate2, *options)
        }

        override fun move(
            source: Path,
            target: Path,
            vararg options: CopyOption
        ) {
            val delegate1 = (source as PathImpl).delegate
            val delegate2 = (target as PathImpl).delegate
            delegate1.fileSystem.provider().move(delegate1, delegate2, *options)
        }

        override fun isSameFile(path: Path, path2: Path): Boolean {
            val delegate1 = (path as PathImpl).delegate
            val delegate2 = (path2 as PathImpl).delegate
            return delegate1.fileSystem.provider().isSameFile(delegate1, delegate2)
        }

        override fun isHidden(path: Path): Boolean {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().isHidden(delegate)
        }

        override fun getFileStore(path: Path): FileStore {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().getFileStore(delegate)
        }

        override fun checkAccess(path: Path, vararg modes: AccessMode) {
            val delegate = (path as PathImpl).delegate
            delegate.fileSystem.provider().checkAccess(delegate, *modes)
        }

        override fun <V : FileAttributeView?> getFileAttributeView(
            path: Path,
            type: Class<V>,
            vararg options: LinkOption
        ): V {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().getFileAttributeView(delegate, type, *options)
        }

        override fun <A : BasicFileAttributes?> readAttributes(
            path: Path,
            type: Class<A>,
            vararg options: LinkOption
        ): A {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().readAttributes(delegate, type, *options)
        }

        override fun readAttributes(
            path: Path,
            attributes: String,
            vararg options: LinkOption
        ): Map<String?, Any?> {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().readAttributes(delegate, attributes, *options)
        }

        override fun setAttribute(
            path: Path,
            attribute: String,
            value: Any,
            vararg options: LinkOption
        ) {
            val delegate = (path as PathImpl).delegate
            delegate.fileSystem.provider().setAttribute(delegate, attribute, value, *options)
        }

        override fun newFileSystem(
            path: Path,
            env: Map<String, *>
        ): FileSystem {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().newFileSystem(delegate, env)
        }

        override fun newInputStream(
            path: Path,
            vararg options: OpenOption
        ): InputStream {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().newInputStream(delegate, *options)
        }

        override fun newOutputStream(
            path: Path,
            vararg options: OpenOption
        ): OutputStream {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().newOutputStream(delegate, *options)
        }

        override fun newFileChannel(
            path: Path,
            options: Set<OpenOption>,
            vararg attrs: FileAttribute<*>
        ): FileChannel {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().newFileChannel(delegate, options, *attrs)
        }

        override fun newAsynchronousFileChannel(
            path: Path,
            options: Set<OpenOption>,
            executor: ExecutorService,
            vararg attrs: FileAttribute<*>
        ): AsynchronousFileChannel {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider()
                .newAsynchronousFileChannel(delegate, options, executor, *attrs)
        }

        override fun createSymbolicLink(
            link: Path,
            target: Path,
            vararg attrs: FileAttribute<*>
        ) {
            val delegate1 = (link as PathImpl).delegate
            val delegate2 = (target as PathImpl).delegate
            delegate1.fileSystem.provider().createSymbolicLink(delegate1, delegate2, *attrs)
        }

        override fun createLink(link: Path, existing: Path) {
            val delegate1 = (link as PathImpl).delegate
            val delegate2 = (existing as PathImpl).delegate
            delegate1.fileSystem.provider().createLink(delegate1, delegate2)
        }

        override fun deleteIfExists(path: Path): Boolean {
            val delegate = (path as PathImpl).delegate
            return delegate.fileSystem.provider().deleteIfExists(delegate)
        }

        override fun readSymbolicLink(link: Path): Path? {
            val delegate = (link as PathImpl).delegate
            return delegate.fileSystem.provider().readSymbolicLink(delegate)
        }
    }

    private class DelegateFileSystem(private val delegate: FileSystem) : FileSystem() {
        override fun provider(): FileSystemProvider {
            return DelegateFileSystemProvider
        }

        override fun close() {
            delegate.close()
        }

        override fun isOpen(): Boolean {
            return delegate.isOpen
        }

        override fun isReadOnly(): Boolean {
            return delegate.isReadOnly
        }

        override fun getSeparator(): String {
            return delegate.separator
        }

        override fun getRootDirectories(): Iterable<Path> {
            return delegate.rootDirectories
        }

        override fun getFileStores(): Iterable<FileStore> {
            return delegate.fileStores
        }

        override fun supportedFileAttributeViews(): Set<String> {
            return delegate.supportedFileAttributeViews()
        }

        override fun getPath(first: String, vararg more: String): Path {
            return delegate.getPath(first, *more)
        }

        override fun getPathMatcher(syntaxAndPattern: String): PathMatcher {
            return delegate.getPathMatcher(syntaxAndPattern)
        }

        override fun getUserPrincipalLookupService(): UserPrincipalLookupService {
            return delegate.userPrincipalLookupService
        }

        override fun newWatchService(): WatchService {
            return delegate.newWatchService()
        }
    }

    private class PathImpl(private val resolver: ShaderPathResolver, val delegate: Path) : Path by delegate {
        override fun getFileSystem(): FileSystem {
            return DelegateFileSystem(delegate.fileSystem)
        }

        override fun resolve(other: String): Path {
            val resultRawPath = when {
                other.startsWith("/") -> {
                    resolver.resolve(other.removePrefix("/"))
                }
                else -> delegate.parent.resolve(other).normalize()
            }
            return PathImpl(resolver, resultRawPath)
        }

        override fun forEach(action: Consumer<in Path>) {
            delegate.forEach(action)
        }

        override fun spliterator(): Spliterator<Path?> {
            return delegate.spliterator()
        }

        override fun toString(): String {
            return delegate.toString()
        }
    }

    object Default : ShaderPathResolver() {
        private val openedZips = mutableSetOf<String>()

        override fun resolve0(path: String): Path {
            val resourceURI = Default::class.java.getResource("/$path")?.toURI()
            if (resourceURI != null) {
                val resourcePathString = resourceURI.toString()
                if (!resourcePathString.startsWith("jar:file:")) {
                    return resourceURI.toPath()
                } else {
                    val zipPath = resourcePathString.substringBefore("!/")
                    if (openedZips.add(zipPath)) {
                        val env = mapOf<String, String>()
                        FileSystems.newFileSystem(URI.create(zipPath), env)
                    }
                    return resourceURI.toPath()
                }
            }
            return Path(path)
        }
    }
}